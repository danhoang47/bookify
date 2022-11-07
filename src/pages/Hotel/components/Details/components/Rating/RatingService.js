export const getPoints = (reviews = []) => {
  const accuracyPoint = Math.floor(
    reviews.reduce((prev, cur) => cur.accuracy_point + prev, 0) / reviews.length
  );
  const locationPoint = Math.floor(
    reviews.reduce((prev, cur) => cur.location_point + prev, 0) / reviews.length
  );
  const valuePoint = Math.floor(
    reviews.reduce((prev, cur) => cur.value_point + prev, 0) / reviews.length
  );
  const communicationPoint = Math.floor(
    reviews.reduce((prev, cur) => cur.communication_point + prev, 0) /
      reviews.length
  );

  return { accuracyPoint, locationPoint, valuePoint, communicationPoint };
};
