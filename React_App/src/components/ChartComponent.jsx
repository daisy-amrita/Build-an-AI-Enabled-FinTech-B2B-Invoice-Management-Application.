import React from "react";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";

const ChartComponent = ({ type, title, data, series, color }) => {
  const options = {
    chart: {
      zoomType: "x",
      type: type,
      backgroundColor: "transparent",
    },
    title: {
      text: title,
      style: { color: "white", whiteSpace: "nowrap" },
    },
    colors: color,
    plotOptions: {
      pie: {
        dataLabels: {
          style: { color: "black" },
          text: ["sda", "asd", "asdas"],
          enabled: true, // Enable data labels
          format: "<b>{point.name}</b> : {point.percentage:.1f}%",
          distance: -30, // Adjust the position of labels
        },
      },
      //   pie: {
      //     dataLabels: {
      //       enabled: false,
      //     },
      //   },
    },
    xAxis: {
      categories: data.names,
      labels: {
        style: { color: "white" },
      },
    },
    yAxis: {
      labels: {
        style: { color: "white" },
      },
    },
    series: data.values
      ? [
          {
            name: series,
            data: data.values,
          },
        ]
      : [],
    legend: { enabled: false },
  };

  return (
    <div
      style={{
        width: 400,
        textAlign: "center",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
      }}>
      {data.values ? (
        <HighchartsReact highcharts={Highcharts} options={options} />
      ) : (
        <p>No data available for the chart.</p>
      )}
    </div>
  );
};

export default ChartComponent;
