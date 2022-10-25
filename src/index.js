import { createRoot } from "react-dom/client";
import App from "./App";
import { RouterProvider } from "react-router-dom";
import router from "./routes";

const root = createRoot(document.getElementById("root"));
root.render(
  <App>
    <RouterProvider router={router} />
  </App>
);
