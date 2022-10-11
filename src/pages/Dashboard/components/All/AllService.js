import { dashboarData } from "./FakeDataDashBoardAll";

export const getStatic = (month) => {
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
  dataByMonth[0].forEach((data) => {
    staticObj.views += data.views;
    staticObj.booking += data.booking;
    staticObj.checkOut += data.checkOut;
    staticObj.rating += data.rating;
    staticObj.register += data.register;
  });

  return staticObj;
};

export const typeBookingData = (month) => {
  let typeBookingObj = {
    type: [],
    numberBooking: [],
  };
  const dataByMonth = dashboarData
    .filter((data) => data.month === month)
    .map((data) => data.types);
  dataByMonth[0].forEach((data) => {
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
  dataByMonth[0].forEach((data) => {
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
