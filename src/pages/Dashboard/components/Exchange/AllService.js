import { dashboarData } from "./FakeDataDashBoardExchange";

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
  return transc;
};
export const getYearsExchange = () => {
  let transc = 0;

  dashboarData?.forEach((data) => {
    transc += data.details.exchange;
  });
  return transc;
};

export const TransactionDataYear = (year) => {
  let transObj = {
    month: [],
    transNumber: [],
  };
  const dataByYear = dashboarData
    .filter((data) => data.years === year)
    .map((data) => data.details);
  dataByYear[0].forEach((data) => {
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
  dashboarData.forEach((data) => {
    transObj.year.push(data.years);
    transObj.transNumber.push(sumByYear(data.details));
  });
  return transObj;
};

const sumByYear = (yearDetail) => {
  const res = yearDetail.reduce((prev, curr) => {
    return prev + curr.exchange;
  }, 0);
  return res;
};
