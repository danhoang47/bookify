import ChartStyle from "./Chart.module.scss";
import { Line } from "react-chartjs-2";

const plugin = {
  beforeInit(chart) {
    console.log("be");
    // Get reference to the original fit function
    const originalFit = chart.legend.fit;

    // Override the fit function
    chart.legend.fit = function fit() {
      // Call original function and bind scope in order to use `this` correctly inside it
      originalFit.bind(chart.legend)();
      // Change the height as suggested in another answers
      this.height += 30;
    };
  },
};

function ChartComponent({ income }) {
  const months = [];
  const incomeByMonth = [];
  const expected = [];
  const total = income.reduce((prev, curr) => {
    return curr.income + prev;
  }, 0);
  const expectIncome = income.reduce((prev, curr) => {
    return curr.expected + prev;
  }, 0);

  income.forEach((data) => {
    months.push(data.month);
    incomeByMonth.push(data.income);
    expected.push(data.expected);
  });

  return (
    <div>
      <div className={ChartStyle["static"]}>
        <h1>${total}</h1>
        <p>Đã thu được trong năm 2022</p>
      </div>
      <div className={ChartStyle["sub-static"]}>
        <h4 className={ChartStyle["realistic"]}>${total}</h4>
        <h4 className={ChartStyle["expected"]}>${expectIncome}</h4>
      </div>
      <div className={ChartStyle["chart-wrapper"]}>
        <Line
          plugins={[plugin]}
          data={{
            labels: months,
            datasets: [
              {
                label: "Đã thu",
                data: incomeByMonth,
                backgroundColor: "#000000",
                borderColor: "#000000",
                borderWidth: 1,
                tension: 0.4,
                // fill: true,
              },
              {
                label: "Ước tính",
                data: expected,
                backgroundColor: "#6A6A6A",
                borderColor: "#6A6A6A",
                borderWidth: 1,
                tension: 0.4,
              },
            ],
          }}
          options={{
            responsive: true,
            maintainAspectRatio: false,
            scales: {
              x: {
                ticks: {
                  display: true,
                  color: "#000",
                  family: "'Poppins', 'sans-serif'",
                },

                // to remove the x-axis grid
                grid: {
                  drawBorder: true,
                  borderColor: "#000",
                  borderWidth: 2,
                  display: false,
                },
              },
              y: {
                beginAtZero: true,

                ticks: {
                  display: false,
                  beginAtZero: true,
                },
                // to remove the y-axis grid
                grid: {
                  drawBorder: true,
                  borderColor: "#000",
                  borderWidth: 2,

                  display: false,
                },
              },
              title: {
                display: false,
              },
            },

            plugins: {
              legend: {
                position: "top",
                align: "start",
                labels: {
                  // This more specific font property overrides the global property
                  font: {
                    size: 12,
                    family: "'Poppins', 'sans-serif'",
                  },
                },
                title: {
                  display: true,
                  align: "start",
                },
              },
            },
          }}
        />
      </div>
    </div>
  );
}

export default ChartComponent;
