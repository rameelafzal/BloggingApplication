import React, { useEffect, useRef, useState } from "react";
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Input, Label, Row } from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import JoditEditor from "jodit-react";
import { toast, ToastContainer } from "react-toastify";
import { createPostAPI } from "../services/post-service";
import '../styles/AddPost.css';
const AddPost = () => {
  const [categories, setCategories] = useState([]);
  const editor = useRef(null);
  const [content, setContent] = useState('');
  const [post, setPost] = useState({
    title: '',
    content: '',
    categoryId: "",
    userId:52
  });

  useEffect(() => {
    loadAllCategories().then((data) => {
      setCategories(data);
    }).catch(error => {
      console.log(error);
    });
  }, []);

  const fieldChanged = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value });
  };

  const contentFieldChanged = (data) => {
    setContent(data);
    setPost({ ...post, 'content': data });
  };

  const createPost = (event) => {
    event.preventDefault();
    if (post.title.trim() === '') {
      toast.error("Post title is required!!");
      return;
    }

    if (post.content.trim() === '') {
      toast.error("Post content is required!!");
      return;
    }

    if (post.categoryId === '') {
      toast.error("Select a category!!");
      return;
    }

    createPostAPI(post)
      .then(data => {
        console.log("Post created");
        toast.success("Post created successfully!");
        resetForm();
      })
      .catch(error => {
        console.error("Error creating post:", error);
        toast.error("Failed to create the post. Please try again.");
      });
  };

  const resetForm = () => {
    setPost({
      title: '',
      content: '',
      categoryId: "",
      userId:52
    });
    setContent('');
  };


  return (
<div className="wrapper">
      <Card>
        <CardBody>
          <h3>What's on your mind?</h3>
          <Form onSubmit={createPost}>
            <Row>
              <Col md={6}>
                <FormGroup>
                  <Label for="title">Post Title</Label>
                  <Input type="text" id="title" name="title" value={post.title} onChange={fieldChanged} />
                </FormGroup>
              </Col>
              <Col md={6}>
                <FormGroup>
                  <Label for="Category">Post Category</Label>
                  <select id="Category" className="form-select" name="categoryId" value={post.categoryId} onChange={fieldChanged}>
                    <option>Select Category</option>
                    {categories.map((category) => (
                      <option value={category.categoryId} key={category.categoryId}>{category.categoryTitle}</option>
                    ))}
                  </select>
                </FormGroup>
              </Col>
            </Row>
            <FormGroup>
              <Label for="Content">Post Content</Label>
              <JoditEditor
                ref={editor}
                value={content}
                onChange={contentFieldChanged}
              />
            </FormGroup>
            <Container className="text-center">
              <Button type="submit" onClick={createPost} className="rounded-0" color="primary">
                Create Post
              </Button>
              <Button type="button" onClick={resetForm} className="rounded-0 ms-2" color="danger">
                Reset Content
              </Button>
            </Container>
          </Form>
          
        </CardBody>
      </Card>
    </div>
  );
};

export default AddPost;
