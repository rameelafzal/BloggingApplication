// Import necessary modules and functions
import React, { useEffect, useState } from "react";
import { loadAllPosts} from "../services/post-service";
import { loadAllCategories} from "../services/category-service";
import { Col, Row, Pagination, PaginationItem, PaginationLink } from "reactstrap";
import Post from "./Post";
import '../styles/NewsFeed.css';

// NewsFeed component
function NewsFeed() {
  // State for posts
  const [postContent, setPostContent] = useState({
    content: [],
    totalPages: '',
    totalElements: '',
    pageSize: '',
    lastPage: false,
    pageNumber: ''
  });

  // State for sorting options
  const [sortBy, setSortBy] = useState("title");
  const [sortOrder, setSortOrder] = useState("desc");

  // State for selected category
  const [selectedCategory, setSelectedCategory] = useState("all");

  // State for categories
  const [categories, setCategories] = useState([]);

  // Load categories when the component mounts
  useEffect(() => {
    loadAllCategories().then((categoriesData) => {
      setCategories(categoriesData);
    });
  }, []);

  // Load posts when the component mounts or when sorting/category changes
  useEffect(() => {
    loadAllPosts(postContent.pageNumber, sortBy, sortOrder, selectedCategory).then((data) => {
      setPostContent(data);
    });
  }, [postContent.pageNumber, sortBy, sortOrder, selectedCategory]);

  // Handle page click event
  const handlePageClick = (page) => {
    setPostContent({ ...postContent, pageNumber: page });
  }

  // Handle sorting option change event
  const handleSortOptionChange = (option) => {
    setSortBy(option);
  };

  // Handle category change event
  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  }

  // JSX rendering
  return (
    <div className="container-fluid">
      <Row>
        <Col md={12} className="blogs-count">
          <p>
            Total Blogs: <span className="count">{postContent?.totalElements}</span>
          </p>
        </Col>
      </Row>
      <Row>
        <Col md={12} className="filter-options">
          <label htmlFor="sortBy">Sort By:</label>
          <select id="sortBy" value={sortBy} onChange={(e) => handleSortOptionChange(e.target.value)}>
            <option value="title">Title</option>
            <option value="addDate">Date</option>
          </select>
        </Col>
      </Row>
      <Row>
        <Col md={12} className="filter-options">
          <label htmlFor="category">Filter by Category:</label>
          <select
            id="category"
            value={selectedCategory}
            onChange={(e) => handleCategoryChange(e.target.value)}
          >
            <option value="all">All Categories</option>
            {categories.map((category) => (
              <option key={category.categoryId} value={category.categoryId}>
                {category.categoryTitle}
              </option>
            ))}
          </select>
        </Col>
      </Row>
      <Row>
        <Col md={12} className="posts-container">
          {postContent.content.map((post) => (
            <Post post={post} key={post.id}></Post>
          ))}
        </Col>
      </Row>
      <Row>
        <Col md={12} className="sticky-pagination">
          <Pagination aria-label="Page navigation example">
            <PaginationItem disabled>
              <PaginationLink first href="#" onClick={() => handlePageClick(0)} />
            </PaginationItem>
            <PaginationItem disabled>
              <PaginationLink href="#" previous onClick={() => handlePageClick(postContent.pageNumber - 1)} />
            </PaginationItem>
            {[...Array(postContent.totalPages).keys()].map((page) => (
              <PaginationItem key={page} active={page === postContent.pageNumber}>
                <PaginationLink href="#" onClick={() => handlePageClick(page)}>
                  {page + 1}
                </PaginationLink>
              </PaginationItem>
            ))}
            <PaginationItem disabled>
              <PaginationLink href="#" next onClick={() => handlePageClick(postContent.pageNumber + 1)} />
            </PaginationItem>
            <PaginationItem disabled>
              <PaginationLink href="#" last onClick={() => handlePageClick(postContent.totalPages - 1)} />
            </PaginationItem>
          </Pagination>
        </Col>
      </Row>
    </div>
  );
}

// Export the component
export default NewsFeed;