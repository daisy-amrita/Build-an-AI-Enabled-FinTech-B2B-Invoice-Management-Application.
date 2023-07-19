import React, { useState, useEffect } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { Button } from "@material-ui/core";
import AllModal from "./AllModal";
import { getInvoice } from "../helper/getInvoice";
import { columns } from "../helper/getcolumnschema";
import { getRowCount } from "../helper/getrowcount";
import { getPrediction } from "../helper/getprediction";
function Homepage() {
  //   getCustomersData();
  const buttonStyle = {
    color: "white",
    backgroundColor: "#FC7500",
    padding: "4px 8px",
    margin: "8px 4px",
  };
  const [data, setData] = useState([]);
  const [pageSize, setPageSize] = React.useState(5);
  const [page, setPage] = React.useState(0);
  const [selectedRows, setSelectedRows] = useState([]);

  const [editButton, setEditButton] = useState(true);
  const [deleteButton, setDeleteButton] = useState(true);
  const [predictButton, setPredictButton] = useState(true);

  const [editModal, setEditModal] = useState(false);
  const [deleteModal, setDeleteModal] = useState(false);

  useEffect(() => {
    if (selectedRows.length === 1) setEditButton(false);
    else setEditButton(true);
    if (selectedRows.length >= 1) {
      setDeleteButton(false);
      setPredictButton(false);
    } else {
      setDeleteButton(true);
      setPredictButton(true);
    }
    console.log(selectedRows);
  }, [selectedRows]);

  useEffect(() => {
    getInvoice(data, setData, pageSize, page);
  }, [page, pageSize]);
  return (
    <div style={{ height: "auto", width: "100%" }}>
      <>
        <DataGrid
          loading={data.length === 0}
          page={page}
          onPageChange={(newPage) => setPage(newPage)}
          pageSize={pageSize}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          rowsPerPageOptions={[5, 10, 20, 50, 100]}
          pagination
          checkboxSelection
          disableColumnSelector
          rowCount={10000}
          rows={data}
          columns={columns}
          onSelectionModelChange={(newSelection) =>
            setSelectedRows(newSelection)
          }
        />
        <AllModal
          editModal={editModal}
          deleteModal={deleteModal}
          setDeleteModal={setDeleteModal}
          setEditModal={setEditModal}
          selectedRows={selectedRows}
        />
        <div className="buttonContainer" style={{ marginTop: "-50px" }}>
          <Button
            disabled={false}
            style={buttonStyle}
            onClick={() => {
              getInvoice(setData, pageSize, page);
            }}
            variant="contained">
            REFRESH DATA
          </Button>
          <Button
            disabled={editButton}
            style={buttonStyle}
            onClick={() => setEditModal(true)}
            variant="contained">
            EDIT
          </Button>
          <Button
            disabled={deleteButton}
            style={buttonStyle}
            onClick={() => setDeleteModal(true)}
            variant="contained">
            DELETE
          </Button>
          <Button
            disabled={predictButton}
            style={buttonStyle}
            variant="contained"
            onClick={() => {
              getPrediction(selectedRows, data, setData);
            }}>
            PREDICT
          </Button>
        </div>
      </>
    </div>
  );
}

export default Homepage;
