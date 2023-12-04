import myAxios from "./helper"
export const createPostAPI=(postData)=>{
    return myAxios.post(
        `/api/user/${postData.userId}/category/${postData.categoryId}/posts`,
        postData
      )
      .then((response) => response.data);
    };

    export const loadAllPosts = (pageNumber,sortBy) => {
      return myAxios
        .get(
          `/api/posts?pageNumber=${pageNumber}&sortBy=${sortBy}`
        )
        .then((response) => response.data);
    };   