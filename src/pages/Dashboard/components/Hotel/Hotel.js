import TableStyle from "./Hotel.module.scss";
import Table from "./Table";
function Hotel({ data }) {
  const fakeData = [
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06 ",
      status: 2,
    },
    {
      hotelhostName: "Lê Quý Đức",
      hotelName: "Đức cạp",
      ID: 1209231202121,
      Time: "10/24/2022 12:30:06",
      status: 3,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
      status: 1,
    },
    {
      hotelhostName: "Lê Quý Đức",
      ID: 1209231202121,
      hotelName: "Đức cạp",
      Time: "10/24/2022 12:30:06",
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
