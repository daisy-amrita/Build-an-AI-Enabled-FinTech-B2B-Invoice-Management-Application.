import { instance } from "./connector.js";
// const { instance } = require("./connector");

export const addInvoice = (invoiceData) => {
  // console.log(data);
  instance
    .request({
      url: "/getslno",
      method: "get",
    })
    .then((data) => {
      instance
        .request({
          url: "/addinvoice",
          method: "post",
          params: {
            SL_NO: data.data.payload + 1,
            CUSTOMER_ORDER_ID: invoiceData.CUSTOMER_ORDER_ID,
            SALES_ORG: invoiceData.SALES_ORG,
            DISTRIBUTION_CHANNEL: invoiceData.DISTRIBUTION_CHANNEL,
            DIVISION: "null",
            RELEASED_CREDIT_VALUE: 0,
            PURCHASE_ORDER_TYPE: "null",
            COMPANY_CODE: invoiceData.COMPANY_CODE,
            ORDER_CREATION_DATE: invoiceData.ORDER_CREATION_DATE,
            ORDER_CREATION_TIME: "null",
            CREDIT_CONTROL_AREA: "null",
            SOLD_TO_PARTY: 0,
            ORDER_AMOUNT: invoiceData.ORDER_AMOUNT,
            REQUESTED_DELIVERY_DATE: "null",
            ORDER_CURRENCY: invoiceData.ORDER_CURRENCY,
            CREDIT_STATUS: "null",
            CUSTOMER_NUMBER: invoiceData.CUSTOMER_NUMBER,
            AMOUNT_IN_USD: 0,
            UNIQUE_CUST_ID: 0,
          },
        })
        .then((data) => console.log(data.data))
        .catch((error) => console.log(error));
    })
    .catch((error) => console.log(error));
};
