import "./App.css";
import { useEffect, useState } from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Homepage from "./components/Homepage";
import SearchResults from "./components/SearchResults";
import Analyticalview from "./components/Analyticalview";
import Addform from "./components/Addform";

import {
  Typography,
  AppBar,
  Tabs,
  Tab,
  Box,
  TextField,
  Button,
} from "@material-ui/core";

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}
function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}>
      {value === index && <Box p={3}>{children}</Box>}
    </div>
  );
}

function App() {
  const [value, setValue] = useState(0);
  const [searchValue, setSearchValue] = useState(undefined);
  const [advanceSearchValue, setAdvanceSearchValue] = useState(undefined);
  const [CUST_NO, setCUST_NO] = useState("");
  const [CUST_OR_ID, setCUST_OR_ID] = useState("");
  const [SALES_ORG, setSALES_ORG] = useState("");
  const [searchTabActive, setSearchTabActive] = useState(false);
  const [advSearchBox, setAdvSearchBox] = useState(false);
  useEffect(() => {
    setAdvSearchBox(false);
  }, [searchTabActive, searchValue]);
  // useEffect();
  const handleChange = (event, newValue) => {
    setValue(newValue);
    setAdvSearchBox(false);
  };
  return (
    <div className="main-container">
      <Header />
      <div className="tab-container">
        <AppBar
          style={{
            backgroundColor: "666666",
            flexDirection: "row",
          }}
          position="static">
          <Tabs
            value={value}
            onChange={handleChange}
            style={{ flexGrow: 1, height: "55px", backgroundColor: "666666" }}
            aria-label="simple tabs example">
            <Tab label="HOME PAGE" {...a11yProps(0)} />
            <Tab label="ADD INOVICE" {...a11yProps(1)} />
            <Tab label="ANALYTICS VIEW" {...a11yProps(2)} />
            {searchTabActive ? (
              <Tab label="SEARCH RESULT" {...a11yProps(3)} />
            ) : null}
          </Tabs>
          <div style={{ display: "flex", gap: "40px" }}>
            <TextField
              style={{ backgroundColor: "none", whiteSpace: "nowrap" }}
              inputProps={{
                style: {
                  width: "250px",
                  backgroundColor: "white",
                  border: 0,
                  height: "10px",
                  marginTop: "4px",
                  borderRadius: "8px",
                  color: "black",
                },
              }}
              id="filled-basic"
              type="number"
              label="Search by Customer Order ID"
              variant="filled"
              value={searchValue}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  setSearchValue(e.target.value);
                  setAdvanceSearchValue(undefined);
                  if (e.target.value?.length > 0) {
                    setSearchTabActive(true);
                    if (value !== 3) setValue(3);
                  } else {
                    setSearchTabActive(false);
                    setValue(0);
                  }
                }
              }}
              onChange={(e) => {
                setSearchValue(e.target.value);
                setAdvanceSearchValue(undefined);
                if (e.target.value?.length > 0) {
                  setSearchTabActive(true);
                  if (value !== 3) setValue(3);
                } else {
                  setSearchTabActive(false);
                  setValue(0);
                }
              }}
            />
            {searchTabActive && !advanceSearchValue ? (
              <Button
                style={{
                  height: "45px",
                  width: "100px",
                  marginTop: "5px",
                  marginRight: "10px",
                  backgroundColor: "#DB4437",
                }}
                onClick={() => {
                  setSearchValue(undefined);
                  setSearchTabActive(false);
                  setValue(0);
                }}
                variant="contained">
                CLEAR
              </Button>
            ) : (
              <Button
                style={{
                  height: "45px",
                  width: "100px",
                  marginTop: "5px",
                  marginRight: "10px",
                  backgroundColor: "#8FD163",
                }}
                onClick={(e) => {
                  // setSearchValue();
                  if (!advSearchBox) {
                    setAdvSearchBox(true);
                    // if (value !== 3) setValue(3);
                  } else {
                    setAdvSearchBox(false);
                    // setValue(0);
                  }
                }}
                variant="contained">
                ADVANCED SEARCH
              </Button>
            )}
            {advSearchBox ? (
              <div
                style={{
                  position: "absolute",
                  top: "60px",
                  right: "10px",
                  border: "3px solid #8FD163",
                  borderRadius: 7,
                  color: "black",
                  backgroundColor: "white",
                  zIndex: 100,
                  width: "300px",
                  padding: "20px",
                  paddingTop: 2,
                  display: "flex",
                  gap: 5,
                  flexDirection: "column",
                }}>
                <Typography variant="h6">Advance Search</Typography>
                <div
                  style={{ display: "flex", gap: 10, flexDirection: "column" }}>
                  <TextField
                    style={{ backgroundColor: "white" }}
                    value={CUST_OR_ID}
                    onChange={(e) => setCUST_OR_ID(e.target.value)}
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
                    type="number"
                    label="CUSTOMER ORDER ID"
                    name="CUST_OR_ID"
                    variant="outlined"
                  />
                  <TextField
                    style={{ backgroundColor: "white" }}
                    value={CUST_NO}
                    onChange={(e) => setCUST_NO(e.target.value)}
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
                    type="number"
                    // InputLabelProps={{ shrink: true }}
                    label="CUSTOMER NUMBER"
                    name="CUST_NO"
                    variant="outlined"
                  />
                  <TextField
                    style={{ backgroundColor: "white" }}
                    value={SALES_ORG}
                    onChange={(e) => setSALES_ORG(e.target.value)}
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
                    type="number"
                    // InputLabelProps={{ shrink: true }}
                    label="SALES ORG"
                    name="SALES_ORG"
                    variant="outlined"
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
                    variant="outlined"
                    onClick={() => {
                      setAdvanceSearchValue({
                        CUST_NO: CUST_NO,
                        CUST_OR_ID: CUST_OR_ID,
                        SALES_ORG: SALES_ORG,
                      });
                      setSearchTabActive(true);
                      setValue(3);
                      setAdvSearchBox(false);
                    }}>
                    SEARCH
                  </Button>
                  <Button
                    variant="outlined"
                    onClick={() => {
                      setAdvSearchBox(false);
                    }}>
                    CANCEL
                  </Button>
                </div>
              </div>
            ) : null}
          </div>
        </AppBar>
        <TabPanel className="tabpanel" value={value} index={0}>
          <Homepage />
        </TabPanel>
        <TabPanel className="tabpanel" value={value} index={1}>
          <Addform />
        </TabPanel>
        <TabPanel className="tabpanel" value={value} index={2}>
          <Analyticalview />
        </TabPanel>
        <TabPanel className="tabpanel" value={value} index={3}>
          <SearchResults
            value={searchValue}
            advanceValue={advanceSearchValue}
          />
        </TabPanel>
      </div>
      <Footer />
    </div>
  );
}

export default App;
