import { myAxios } from "./helper";

export const loadAllCategories = () => {
  return myAxios.get(`/api/categories/`).then((respone) => {
    return respone.data;
  });
};