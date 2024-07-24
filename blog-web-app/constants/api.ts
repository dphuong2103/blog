export const env = process.env.NODE_ENV;
// export const api_url = !(env === "production") ? process.env.API_URL_DEV : process.env.API_URL_PROD;
export const api_url = !(env === "production")
  ? process.env.API_URL_DEV
  : "http://blog-be:8080/api/v1";
export const blog_api_url = `${api_url}/blogs`;
export const user_api_url = `${api_url}/users`;
export const tag_api_url = `${api_url}/tags`;
export const authenticate_api_url = `${api_url}/authenticate`;
