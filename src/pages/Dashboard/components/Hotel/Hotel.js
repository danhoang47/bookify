import TableStyle from "./Hotel.module.scss";
import Table from "./Table";
function Hotel({ data }) {
  const fakeData = [
    {
      hotelhostName: "Lê Quý Đức",
      hotelName: "Đức cạp",
      Time: "October 24,2022",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      hotelName: "Đức cạp",
      Time: "October 24,2022",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      hotelName: "Đức cạp",
      Time: "October 24,2022",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      hotelName: "Đức cạp",
      Time: "October 24,2022",
      status: 1,
    },
  ];
  return (
    <div className={TableStyle["container"]}>
      <h2>
        <b>Danh sách khách sạn</b>
      </h2>
      <Table data={fakeData} />
    </div>
  );
}
export default Hotel;
