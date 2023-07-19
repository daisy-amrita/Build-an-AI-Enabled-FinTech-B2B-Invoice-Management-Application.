import React from "react";
import { Typography } from "@material-ui/core";
import hrclogo from "../images/hrclogo.svg";
import abccompany from "../images/abclogo.svg";
const Header = () => {
  return (
    <>
      <div className="header">
        <img className="abclogo" src={abccompany} alt="" srcSet="" />
        <img className="hrclogo" src={hrclogo} alt="" srcSet="" />
      </div>
      <Typography
        variant="h6"
        style={{
          color: "red",
          fontWeight: 600,
          padding: "0px auto 10px 10px",
          marginLeft: "5px",
        }}>
        Invoice List
      </Typography>
    </>
  );
};

export default Header;
