import { instance } from "./connector.js";
// const { instance } = require("./connector");
export const deleteInvoice = (SL_NOs) => {
  SL_NOs.forEach((SL_NO) => {
    console.log(SL_NO);
    instance
      .delete("/deleteinvoice", {
        params: { SL_NO: SL_NO },
        Headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": null,
        },
      })
      .then((data) => console.log(data.data))
      .catch((error) => console.log(error));
  });
};
// deleteInvoice();
