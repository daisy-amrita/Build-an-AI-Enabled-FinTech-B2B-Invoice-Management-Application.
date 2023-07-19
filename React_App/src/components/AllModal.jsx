import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import { Typography, Button, TextField } from "@material-ui/core";
import { deleteInvoice } from "../helper/deleteInvoice";
import { editInvoice } from "../helper/editInvoice";
import { useEffect } from "react";
// import axios from "axios";
function rand() {
  return Math.round(Math.random() * 20) - 10;
}

function getModalStyle() {
  const top = 50 + rand();
  const left = 50 + rand();

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

const useStyles = makeStyles((theme) => ({
  paper: {
    position: "absolute",
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #FC7500",
    borderRadius: "4px",
    boxShadow: theme.shadows[5],
    height: 250,
    padding: "10px",
  },
}));

export default function AllModal({
  editModal,
  deleteModal,
  setDeleteModal,
  setEditModal,
  selectedRows,
}) {
  const [editButton, setEditButton] = useState(true);
  const [orderCurrency, setOrderCurrency] = useState("");
  const [distribution, setDistribution] = useState("");
  const [companyCode, setCompanyCode] = useState("");
  const classes = useStyles();
  const [modalStyle] = useState(getModalStyle);
  useEffect(() => {
    let flag =
      companyCode?.length > 0 &&
      distribution?.length > 0 &&
      orderCurrency?.length > 0;
    setEditButton(!flag);
  }, [companyCode, distribution, orderCurrency]);
  return (
    <div>
      <Modal
        open={editModal}
        onClose={() => setEditModal(false)}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description">
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">Edit Invoice</h2>
          <div
            style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
            <div style={{ display: "flex", gap: "10px" }}>
              <TextField
                style={{ backgroundColor: "white" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                label="ORDER CURRENCY"
                type="text"
                variant="outlined"
                onChange={(e) => {
                  setOrderCurrency(e.target.value);
                }}
              />
              <TextField
                style={{ backgroundColor: "white" }}
                inputProps={{
                  style: {
                    backgroundColor: "white",
                    border: 0,
                    height: "10px",
                    marginTop: "4px",
                    borderRadius: "8px",
                    color: "black",
                  },
                }}
                id="filled-basic"
                label="COMPANY CODE"
                type="number"
                variant="outlined"
                onChange={(e) => {
                  setCompanyCode(e.target.value);
                }}
              />
            </div>
            <TextField
              style={{ backgroundColor: "white" }}
              inputProps={{
                style: {
                  backgroundColor: "white",
                  border: 0,
                  height: "10px",
                  marginTop: "4px",
                  borderRadius: "8px",
                  color: "black",
                },
              }}
              id="filled-basic"
              label="DISTRIBUTION CHANNEL"
              variant="outlined"
              type="text"
              onChange={(e) => {
                setDistribution(e.target.value);
              }}
            />
          </div>
          <div
            style={{
              marginTop: "20px",
              justifyContent: "center",
              display: "flex",
              gap: "20px",
            }}>
            <Button
              variant="contained"
              disabled={editButton}
              onClick={() => {
                editInvoice({
                  SL_NO: selectedRows[0],
                  DISTRIBUTION_CHANNEL: distribution,
                  COMPANY_CODE: companyCode,
                  ORDER_CURRENCY: orderCurrency,
                });
                setEditModal(false);
              }}>
              Edit
            </Button>
            <Button
              onClick={() => {
                setEditModal(false);
              }}
              variant="contained">
              CANCEL
            </Button>
          </div>
        </div>
      </Modal>
      <Modal
        open={deleteModal}
        onClose={() => {
          setDeleteModal(false);
        }}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description">
        <div style={modalStyle} className={classes.paper}>
          <Typography variant="h6" id="simple-modal-title">
            Delete Records
          </Typography>
          <Typography style={{ paddingTop: "10px" }}>
            Are you sure you want to delete these record[s]?
          </Typography>
          <div
            style={{
              marginTop: "60px",
              justifyContent: "center",
              display: "flex",
              gap: "20px",
              display: "flex",
            }}>
            <Button
              onClick={() => {
                setDeleteModal(false);
              }}
              variant="contained">
              CANCEL
            </Button>
            <Button
              variant="contained"
              onClick={() => {
                deleteInvoice(selectedRows);
                setDeleteModal(false);
              }}>
              DELETE
            </Button>
          </div>
        </div>
      </Modal>
    </div>
  );
}
