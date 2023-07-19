import { instance } from "./connector.js";
export const searchInvoice = (setData, setSearching, number, page, limit) => {
  setSearching(true);
  console.log("Searching");
  instance
    .get("/searchinvoice", {
      params: { CUST_ORD_ID: number, LIMIT: 10000 },
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
