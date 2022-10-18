import { dashboarData } from "./FakeDataDashBoardExchange";

const monthsKey = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
];
const yearsKey = [
  "2014",
  "2015",
  "2016",
  "2017",
  "2018",
  "2019",
  "2020",
  "2021",
  "2022",
];

export const getYearExchange = (year) => {
  let transc = 0;
  const dataByYear = dashboarData
    .filter((data) => data.years === year)
    .map((monthStatic) => {
      return monthStatic.details;
    });
  dataByYear[0]?.forEach((data) => {
    transc += data.exchange;
  });
};
export const getYearsExchange = (year) => {
  let transc = 0;

  dashboarData?.forEach((data) => {
    transc += data.exchange;
  });
};

export const TransactionDataYear = (year) => {
  let transObj = {
    month: [],
    transNumber: [],
  };
  const dataByYear = dashboarData
    .filter((data) => data.years === year)
    .map((data) => data.details);
  dataByMonth[0].forEach((data) => {
    transObj.month.push(data.month);
    transObj.transNumber.push(data.exchange);
  });
  return transObj;
};
export const TransactionDataYears = () => {
  let transObj = {
    year: [],
    transNumber: [],
  };
  const dataByYears = dashboarData.map((data) => data.details);
  dashboarData.forEach((data) => {
    transObj.year.push(data.years);
    transObj.transNumber.push(data.forEach(data.details));
  });
  return transObj;
};
