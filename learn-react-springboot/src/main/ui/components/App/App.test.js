import React from "react";
import { render } from "@testing-library/react";
import App from "./App.js";

describe("App Component", () => {
  it("returns the App component", () => {
    const { container } = render(<App />);
    expect(container.getElementsByClassName("App").length).toBe(1);
  });

  it("renders Layout component", () => {
    const { container } = render(<App />);
    expect(container.getElementsByClassName("main-screen").length).toBe(1);
  });
});
