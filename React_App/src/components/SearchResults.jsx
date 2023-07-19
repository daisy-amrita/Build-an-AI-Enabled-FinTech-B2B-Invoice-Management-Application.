import React, { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { searchInvoice } from "../helper/searchinvoice";
import { advanceSearch } from "../helper/advancesearch";
import { Typography, Button } from "@material-ui/core";
import AllModal from "./AllModal";
import { columns } from "../helper/getcolumnschema";
const SearchResults = ({ value, advanceValue }) => {
  const buttonStyle = {
    color: "white",
    backgroundColor: "#FC7500",
    padding: "4px 8px",
    margin: "8px 4px",
  };
  const [searchData, setSearchData] = useState([]);
  const [pageSize, setPageSize] = React.useState(5);
  const [page, setPage] = React.useState(0);
  const [selectedRows, setSelectedRows] = useState([]);

  const [editButton, setEditButton] = useState(true);
  const [deleteButton, setDeleteButton] = useState(true);
  const [predictButton, setPredictButton] = useState(true);

  const [editModal, setEditModal] = useState(false);
  const [deleteModal, setDeleteModal] = useState(false);

  useEffect(() => {
    if (value)
      searchInvoice(setSearchData, setSearching, value, page, pageSize);
    if (advanceValue)
      advanceSearch(setSearchData, setSearching, advanceValue, page, pageSize);
  }, [value, advanceValue]);

  useEffect(() => {
    if (selectedRows.length === 1) setEditButton(false);
    else setEditButton(true);
    if (selectedRows.length >= 1) setDeleteButton(false);
    else setDeleteButton(true);

    console.log(selectedRows);
  }, [selectedRows]);
  //   useEffect(() => {
  //     searchInvoice(setSearchData, setSearching, value);
  //     console.log(data);
  //   }, []);

  const [searching, setSearching] = useState(true);

  return (
    <div style={{ height: 500, width: "100%" }}>
      <>
        <DataGrid
          loading={searching}
          page={page}
          onPageChange={(newPage) => setPage(newPage)}
          pageSize={pageSize}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          rowsPerPageOptions={[5, 10, 20]}
          pagination
          checkboxSelection
          disableColumnSelector
          rows={searchData}
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
            onClick={() => searchInvoice(setSearchData, setSearching, value)}
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
            variant="contained">
            PREDICT
          </Button>
        </div>
      </>
    </div>
  );
};

export default SearchResults;
