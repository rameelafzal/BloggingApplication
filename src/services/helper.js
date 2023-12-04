import axios from "axios";

export const BASE_URL = "http://localhost:8080";

export const myAxios = axios.create({
  baseURL: BASE_URL, // Set the base URL for all requests
});

export default myAxios; // Optionally, you can export the configured Axios instance as the default export
