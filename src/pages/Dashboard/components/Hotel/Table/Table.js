function Table({ data }) {
  return (
    <table>
      <thead>
        <th>Tên chủ khách sạn</th>
        <th>Tên khách sạn</th>
        <th>Thời gian</th>
        <th>Trạng thái</th>
        <th>Chi tiết</th>
      </thead>
      <tbody>
        {data.map((row) => (
          <tr>
            {Object.keys(row).map((value) => (
              <td>{row[value]}</td>
            ))}
            <td>Chi tiết</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
export default Table;
