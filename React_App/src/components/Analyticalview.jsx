import React, { useEffect, useState } from "react";
import { TextField, Button } from "@material-ui/core";
import ChartComponent from "./ChartComponent";
import { getChartData } from "../helper/getchartdata";

const Analyticalview = () => {
  const [barData, setBarData] = useState({});
  const [pieData, setPieData] = useState({});
  const [DISTRIBUTION_CHANNEL, setDISTRIBUTION_CHANNEL] = useState("");
  const [CUSTOMER_NUMBER, setCUSTOMER_NUMBER] = useState("");
  useEffect(() => {
    console.log(pieData !== {});
    console.log(barData);
  }, [pieData, barData]);
  return (
    <div style={{ display: "flex", justifyContent: "center", paddingTop: 20 }}>
      <div>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            gap: 10,
            padding: 10,
            border: "2px solid white",
            borderRadius: 4,
          }}>
          <TextField
            style={{ backgroundColor: "none" }}
            value={DISTRIBUTION_CHANNEL}
            inputProps={{
              style: {
                backgroundColor: "white",
                border: 0,
                height: "10px",
                marginTop: "4px",
                width: "300px",
                borderRadius: "8px",
                color: "black",
              },
            }}
            id="filled-basic"
            label="DISTRIBUTION CHANNEL"
            type="text"
            variant="outlined"
            onChange={(e) => {
              setDISTRIBUTION_CHANNEL(e.target.value);
            }}
          />
          <TextField
            style={{ backgroundColor: "none" }}
            value={CUSTOMER_NUMBER}
            inputProps={{
              style: {
                backgroundColor: "white",
                border: 0,
                height: "10px",
                marginTop: "4px",
                width: "300px",
                borderRadius: "8px",
                color: "black",
              },
            }}
            id="filled-basic"
            label="CUSTOMER NUMBER"
            type="number"
            variant="outlined"
            onChange={(e) => {
              setCUSTOMER_NUMBER(e.target.value);
            }}
          />
          <Button
            style={{ color: "white", borderColor: "white", borderWidth: "2px" }}
            variant="outlined"
            onClick={() => {
              if (DISTRIBUTION_CHANNEL !== "" || CUSTOMER_NUMBER !== "") {
                getChartData(
                  setBarData,
                  setPieData,
                  setLoading,
                  DISTRIBUTION_CHANNEL,
                  CUSTOMER_NUMBER
                );
                console.log("This");
              }
            }}>
            VIEW
          </Button>
        </div>
      </div>
      <div style={{ display: "flex" }}>
        <ChartComponent
          type="column"
          title="Total Amount per Distribution Channel"
          data={barData}
          color={["#FC7500"]}
          series="Total Amount"
        />
        <ChartComponent
          type="pie"
          title="Open and Closed Invoices"
          data={pieData}
          color={["#8FD163", "#DB4437"]}
          series="Percentage"
        />
      </div>
    </div>
  );
};

export default Analyticalview;
