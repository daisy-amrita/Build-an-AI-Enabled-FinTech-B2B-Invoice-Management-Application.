import { instance } from "./connector.js";
export const getInvoice = (dataPrev, setData, limit, page) => {
  console.log(limit, page);
  instance
    .get("/getinvoice", { params: { START: 0, SIZE: limit * (page + 1) } })
    .then((data) =>
      setData(
        data.data.payload.map((ele) => {
          ele["id"] = ele["SL_NO"];
          return ele;
        })
      )
    )
    .catch((error) => console.log(error));
};
