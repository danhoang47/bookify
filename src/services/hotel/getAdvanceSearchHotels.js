import { format } from "date-fns";

const formatDate = (date) => {
  return format(date, "yyyy-MM-dd");
};

export default async function getAdvanceSearchHotels(
  place,
  selectedDays,
  guests
) {
  const { from, to } = selectedDays;
  const numberOfPeople = Object.keys(guests).reduce(
    (prev, key) => (key === "pet" ? prev : prev + guests[key]),
    0
  );
  const url = `http://localhost:8080/bookify/api/hotel/search/advance?place=${place}&check-in=${formatDate(
    from
  )}&check-out=${formatDate(to)}&guest=${numberOfPeople}`;
  const data = await fetch(url, { method: "GET" })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      return data;
    });
  return data;
}
