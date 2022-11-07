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

  if (month === 1) {
    return increasePercent;
  }
  // monthsKey[monthsKey.indexOf(month) - 1]
  const prevMonth = getStatic(month - 1);
  const curMonth = getStatic(month);
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

export const getIncreasePercent2 = (prevData, curData, month) => {
  let increasePercent = {
    views: 0,
    booking: 0,
    checkOut: 0,
    rating: 0,
    register: 0,
  };

  if (month === 1) {
    return increasePercent;
  }

  // prevData.paymentNumber = 1;
  // prevData.reviewNumber = 1;
  // prevData.userRegisNumber = 1;
  // prevData.bookingNumber = 1;
  // prevData.userRegisNumber = 5;

  if (prevData.accessNumber !== 0 && curData.accessNumber !== 0) {
    increasePercent.views = parseFloat(
      (
        ((1 -
          (curData.accessNumber - prevData.accessNumber) /
            prevData.accessNumber) *
          100 *
          100) /
        100
      ).toFixed(1)
    );
  }

  if (prevData.bookingNumber !== 0 && curData.bookingNumber !== 0) {
    increasePercent.booking = parseFloat(
      (
        ((1 -
          (curData.bookingNumber - prevData.bookingNumber) /
            curData.bookingNumber) *
          100 *
          100) /
        100
      ).toFixed(1)
    );
  }

  if (prevData.paymentNumber !== 0 && curData.paymentNumber !== 0) {
    increasePercent.checkOut = parseFloat(
      (
        ((1 -
          (curData.paymentNumber - prevData.paymentNumber) /
            curData.paymentNumber) *
          100 *
          100) /
        100
      ).toFixed(1)
    );
  }

  if (prevData.reviewNumber !== 0 && curData.reviewNumber !== 0) {
    increasePercent.rating = parseFloat(
      (
        (((curData.reviewNumber - prevData.reviewNumber) /
          prevData.reviewNumber) *
          100 *
          100) /
        100
      ).toFixed(1)
    );
  }

  if (prevData.userRegisNumber !== 0 && curData.userRegisNumber !== 0) {
    increasePercent.register = parseFloat(
      (
        (((curData.userRegisNumber - prevData.userRegisNumber) /
          prevData.userRegisNumber) *
          100 *
          100) /
        100
      ).toFixed(1)
    );
  }

  return increasePercent;
};
