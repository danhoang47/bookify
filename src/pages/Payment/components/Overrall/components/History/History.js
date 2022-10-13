import HistoryStyle from "../../Overrall.module.scss";

function History() {
  return (
    <div className={HistoryStyle["history-list"]}>
      <div lassName={HistoryStyle["stat-list"]}>
        <div>abc</div>
        <div>abc</div> <div>abc</div> <div>abc</div> <div>abc</div>{" "}
        <div>abc</div> <div>abc</div> <div>abc</div> <div>abc</div>
      </div>
      <button>Hien thi them</button>
    </div>
  );
}

export default History;
