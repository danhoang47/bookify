import { dashboarData } from "./FakeDataDashBoardAll";

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

export const getStatic = (month) => {
  console.log(month);
  let staticObj = {
    views: 0,
    booking: 0,
    checkOut: 0,
    rating: 0,
    register: 0,
  };
  const dataByMonth = dashboarData
    .filter((data) => data.month === month)
    .map((monthStatic) => {
      return monthStatic.details;
    });

  dataByMonth[0]?.forEach((data) => {
    staticObj.views += data.views;
    staticObj.booking += data.booking;
    staticObj.checkOut += data.checkOut;
    staticObj.rating += data.rating;
    staticObj.register += data.register;
  });

  return staticObj;
};

export const getIncreasePercent = (month) => {
  let increasePercent = {
    views: 0,
    booking: 0,
    checkOut: 0,
    rating: 0,
    register: 0,
  };

  if (month === "Jan") {
    return increasePercent;
  }
  // monthsKey[monthsKey.indexOf(month) - 1]
  const prevMonth = getStatic(monthsKey[monthsKey.indexOf(month) - 1]);
  const curMonth = getStatic(monthsKey[monthsKey.indexOf(month)]);
  increasePercent.views =
    Math.round(
      ((curMonth.views - prevMonth.views) / prevMonth.views) * 100 * 100
    ) / 100;
  increasePercent.booking =
    Math.round(
      ((curMonth.booking - prevMonth.booking) / prevMonth.booking) * 100 * 100
    ) / 100;
  increasePercent.checkOut =
    Math.round(
      ((curMonth.checkOut - prevMonth.checkOut) / prevMonth.checkOut) *
        100 *
        100
    ) / 100;
  increasePercent.rating =
    Math.round(
      ((curMonth.rating - prevMonth.rating) / prevMonth.rating) * 100 * 100
    ) / 100;
  increasePercent.register =
    Math.round(
      ((curMonth.register - prevMonth.register) / prevMonth.register) *
        100 *
        100
    ) / 100;

  return increasePercent;
};

export const typeBookingData = (month) => {
  let typeBookingObj = {
    type: [],
    numberBooking: [],
  };
  const dataByMonth = dashboarData
    .filter((data) => data.month === month)
    .map((data) => data.types);
  dataByMonth[0]?.forEach((data) => {
    typeBookingObj.type.push(data.type);
    typeBookingObj.numberBooking.push(data.numberOfBooking);
  });
  return typeBookingObj;
};

export const BookingNumberData = (month) => {
  let bookingObj = {
    day: [],
    numberBooking: [],
  };
  const dataByMonth = dashboarData
    .filter((data) => data.month === month)
    .map((data) => data.details);
  dataByMonth[0]?.forEach((data) => {
    bookingObj.day.push(data.day);
    bookingObj.numberBooking.push(data.booking);
  });
  return bookingObj;
};

export const ReportData = (month) => {
  const dataByMonth = dashboarData
    .filter((data) => data.month === month)
    .map((data) => data.report);

  return dataByMonth[0];
};
