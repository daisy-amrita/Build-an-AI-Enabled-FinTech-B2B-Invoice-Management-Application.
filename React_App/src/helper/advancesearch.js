import { instance } from "./connector.js";
export const advanceSearch = (setData, setSearching, data, page, limit) => {
  setSearching(true);
  console.log("Searching");
  instance
    .get("/advancesearch", {
      params: {
        CUST_NO: data.CUST_NO === "" ? -1 : data.CUST_NO,
        CUST_ORD_ID: data.CUST_OR_ID === "" ? -1 : data.CUST_OR_ID,
        SALE_ORG: data.SALES_ORG === "" ? -1 : data.SALES_ORG,
      },
    })
    .then((data) => {
      setData([]);
      setData(
        data.data.payload.map((ele) => {
          ele["id"] = ele["SL_NO"];
          return ele;
        })
      );
      console.log("searched");
    })
    .catch((error) => console.log(error))
    .finally(() => setSearching(false));
};
