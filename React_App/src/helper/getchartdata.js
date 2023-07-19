import { instance } from "./connector.js";
export const getChartData = (
  setBarData,
  setPieData,
  setLoading,
  dist,
  cust
) => {
  setLoading(true);
  instance
    .get(
      `/chartdata?${dist ? `DIST=${dist}` : ``}${dist && cust ? `&` : ``}${
        cust ? `CUST_NO=${cust}` : ``
      }`
    )
    .then((data) => {
      let BD = data.data.barData;
      let barvalues = [];
      let barnames = [];
      Object.keys(BD).forEach((e) => {
        barnames.push(e);
        barvalues.push(BD[e]);
      });
      setBarData({ names: barnames, values: barvalues });
      setPieData({
        names: ["Closed Invoices", "Open Invoices"],
        values: [
          ["Closed Invoices", data.data.pieData],
          ["Open Invoices", 1 - data.data.pieData],
        ],
      });
      setLoading(false);
    })
    .catch((error) => console.log(error));
};
