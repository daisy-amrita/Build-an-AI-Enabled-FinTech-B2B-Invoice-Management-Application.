import { instance } from "./connector.js";
// const { instance } = require("./connector");

export const editInvoice = (data) => {
  // console.log(data);
  instance
    .request({
      url: "/updateinvoice",
      method: "post",
      params: data,
    })
    .then((data) => console.log(data.data))
    .catch((error) => console.log(error));
};
