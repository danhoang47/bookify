import FormUpdateStyle from "./FormUpdate.module.scss";

function FormUpdate({ account }) {
  return (
    <div>
      Form
      <form action="">
        <span className={FormUpdateStyle["name-field"]}>
          <span className={FormUpdateStyle["subname-input-field"]}>
            <label htmlFor="subname">
              <b className={FormUpdateStyle["label"]}>Họ và tên đệm</b>
            </label>
            <input
              type="text"
              placeholder="Điền tên đệm của bạn"
              name="subname"
              className={FormUpdateStyle["input-update"]}
            />
          </span>

          <span className={FormUpdateStyle["name-input-field"]}>
            <label htmlFor="name">
              <b className={FormUpdateStyle["label"]}>Tên</b>
            </label>
            <input
              type="text"
              placeholder="Điền tên bạn"
              name="name"
              className={FormUpdateStyle["input-update"]}
            />
          </span>
        </span>
      </form>
    </div>
  );
}

export default FormUpdate;
