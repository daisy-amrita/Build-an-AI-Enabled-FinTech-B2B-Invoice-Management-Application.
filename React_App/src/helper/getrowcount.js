import { instance } from "./connector.js";
export const getRowCount = (setData) => {
  instance
    .get("/getslno", { params: { SL_NO: 1, SIZE: 100 } })
    .then((data) => setData(data.data.payload))
    .catch((error) => console.log(error));
};
