// import { instance } from "./connector.js";
import axios from "axios";
export const getPrediction = (selected, data, setData) => {
  let request = data.filter((e) => selected.includes(e.id));
  axios
    .request({
      url: "http://localhost:5000/predict",
      method: "POST",
      data: request,
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      const newData = response.data.data;
      console.log(newData[0]);
      let i = 0;
      setData(
        data.map((ele) => {
          //   console.log(ele.id === newData[i][0], ele.id, newData[i][0]);
          if (i < newData.length && ele.id === newData[i][0]) {
            console.log(newData[0][1], newData[0][2]);
            ele["ORDER_AMOUNT"] = newData[i][1].toFixed(2);
            ele["AMOUNT_IN_USD"] = newData[i][2].toFixed(2);
            i++;
            console.log(ele);
          }
          return ele;
        })
      );
    })
    .catch((error) => console.log(error));
};
