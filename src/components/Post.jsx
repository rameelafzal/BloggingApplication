import React, { useState } from 'react';
import { Button, Card, CardBody, CardText } from 'reactstrap';

function Post({ post = { title: "this is my post title", content: 'this is default content' } }) {
  const isHTML = /<[a-z][\s\S]*>/i.test(post.content); // Check if content is HTML
  const [showFullPost, setShowFullPost] = useState(false);

  const toggleShowFullPost = () => {
    setShowFullPost(!showFullPost);
  };

  return (
    <Card className='border-0 shadow-sm mt-3'>
      <CardBody>
        <h3>{post.title}</h3>
        <CardText>
          {showFullPost ? (
            isHTML ? (
              <div dangerouslySetInnerHTML={{ __html: post.content }}></div>
            ) : (
              <p>{post.content}</p> // Render non-HTML content as plain text
            )
          ) : (
            isHTML ? (
              <div dangerouslySetInnerHTML={{ __html: post.content.substring(0, 100) }}></div>
            ) : (
              <p>{post.content.substring(0, 50)}</p> // Render truncated content
            )
          )}
        </CardText>
        <div>
          <Button onClick={toggleShowFullPost}>
            {showFullPost ? "Read less" : "Read more"}
          </Button>
        </div>
      </CardBody>
    </Card>
  );
}

export default Post;
