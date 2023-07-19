import React, { useState } from "react";
import { Button, TextField } from "@material-ui/core";
import { useEffect } from "react";
import { addInvoice } from "../helper/addInvoice";
const Addform = () => {
  const [COMPANY_CODE, setCOMPANY_CODE] = useState("");
  const [CUST_OR_ID, setCUST_OR_ID] = useState("");
  const [SALES_ORG, setSALES_ORG] = useState("");
  const [DISTRIBUTION, setDISTRIBUTION] = useState("");
  const [CUST_NO, setCUST_NO] = useState("");
  const [ORDER_CUR, setORDER_CUR] = useState("");
  const [ORDER_AMT, setORDER_AMT] = useState("");
  const [ORDER_CREATION_DATE, setORDER_CREATION_DATE] = useState("");
  const [addButton, setAddButton] = useState(true);
  function validateInput(value) {}
  useEffect(() => {
    let flag =
      COMPANY_CODE.length &&
      CUST_OR_ID.length &&
      SALES_ORG.length &&
      DISTRIBUTION.length &&
      CUST_NO.length &&
      ORDER_CUR.length &&
      ORDER_AMT.length &&
      ORDER_CREATION_DATE.length;
    console.log("flag: ", flag);
    setAddButton(!flag);
  }, [
    COMPANY_CODE,
    CUST_OR_ID,
    SALES_ORG,
    DISTRIBUTION,
    CUST_NO,
    ORDER_CUR,
    ORDER_AMT,
    ORDER_CREATION_DATE,
  ]);
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        paddingTop: 100,
        width: "80%",
        margin: "auto",
      }}>
      <div style={{ display: "flex", flexDirection: "column", width: "100%" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            flexDirection: "row",
            gap: 6,
            width: "100%",
          }}>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              gap: 1,
              width: "100%",
            }}>
            <div style={{ display: "flex", gap: 6, marginBottom: 8 }}>
              <TextField
                style={{ backgroundColor: "#666666", width: "50%" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                type="number"
                id="filled-basic"
                value={CUST_OR_ID}
                onChange={(e) => setCUST_OR_ID(e.target.value)}
                label="CUSTOMER ORDER ID"
                name="CUSTOMER_ORDER_ID"
                variant="outlined"
              />
              <TextField
                style={{ backgroundColor: "#666666", width: "50%" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={SALES_ORG}
                onChange={(e) => setSALES_ORG(e.target.value)}
                type="number"
                label="SALES ORG"
                name="SALES_ORG"
                variant="outlined"
              />
            </div>
            <div style={{ display: "flex", gap: 6 }}>
              <TextField
                style={{ backgroundColor: "#666666", width: "50%" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={CUST_NO}
                onChange={(e) => setCUST_NO(e.target.value)}
                type="number"
                label="CUSTOMER NUMBER"
                name="CUSTOMER_NUMBER"
                variant="outlined"
              />
              <TextField
                style={{ backgroundColor: "#666666", width: "50%" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={COMPANY_CODE}
                onChange={(e) => {
                  setCOMPANY_CODE(e.target.value);
                }}
                type="number"
                label="COMPANY CODE"
                name="COMPANY_CODE"
                variant="outlined"
              />
            </div>
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              gap: 1,
              width: "100%",
            }}>
            <div style={{ display: "flex", gap: 6, marginBottom: 8 }}>
              <TextField
                style={{ backgroundColor: "#666666", width: "100%" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={DISTRIBUTION}
                onChange={(e) => setDISTRIBUTION(e.target.value)}
                label="DISTRIBUTION CHANNEL"
                name="DISTRIBUTION_CHANNEL"
                variant="outlined"
              />
            </div>
            <div style={{ display: "flex", gap: 6 }}>
              <TextField
                style={{ backgroundColor: "#666666" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={ORDER_CUR}
                onChange={(e) => setORDER_CUR(e.target.value)}
                label="ORDER CURRENCY"
                name="ORDER_CURRENCY"
                variant="outlined"
              />
              <TextField
                style={{ backgroundColor: "#666666" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                value={ORDER_AMT}
                onChange={(e) => setORDER_AMT(e.target.value)}
                type="number"
                label="ORDER AMOUNT"
                name="ORDER_AMOUNT"
                variant="outlined"
              />
              <TextField
                style={{ backgroundColor: "#666666" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    width: "100%",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: ORDER_CREATION_DATE !== "" ? "black" : "white",
                  },
                }}
                id="filled-basic"
                value={ORDER_CREATION_DATE}
                onChange={(e) => setORDER_CREATION_DATE(e.target.value)}
                type="date"
                // InputLabelProps={{ shrink: true }}
                label="ORDER CREATION DATE"
                name="ORDER_CREATION_DATE"
                variant="outlined"
              />
            </div>
          </div>
        </div>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            paddingTop: "10px",
            gap: 6,
          }}>
          <Button
            disabled={addButton}
            style={{ width: "100%", flexGrow: 1, backgroundColor: "#FC7500" }}
            variant="contained"
            onClick={() => {
              addInvoice({
                CUSTOMER_ORDER_ID: CUST_OR_ID,
                SALES_ORG: SALES_ORG,
                DISTRIBUTION_CHANNEL: DISTRIBUTION,
                COMPANY_CODE: COMPANY_CODE,
                ORDER_CREATION_DATE: ORDER_CREATION_DATE,
                ORDER_AMOUNT: ORDER_AMT,
                ORDER_CURRENCY: ORDER_AMT,
                CUSTOMER_NUMBER: CUST_NO,
              });

              setCOMPANY_CODE("");
              setCUST_NO("");
              setSALES_ORG("");
              setCUST_OR_ID("");
              setORDER_AMT("");
              setDISTRIBUTION("");
              setORDER_CREATION_DATE("0");
              setORDER_CUR("");
            }}>
            ADD
          </Button>
          <Button
            style={{ width: "100%", flexGrow: 1, backgroundColor: "#DB4437" }}
            variant="contained"
            onClick={() => {
              setCOMPANY_CODE("");
              setCUST_NO("");
              setSALES_ORG("");
              setCUST_OR_ID("");
              setORDER_AMT("");
              setDISTRIBUTION("");
              setORDER_CREATION_DATE("0");
              setORDER_CUR("");
            }}>
            CLEAR DATA
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Addform;
