import React from "react";
import { render, screen } from "@testing-library/react";
import Header from "./Header";

describe("Header", () => {
  it("logo image", () => {
    const { getByAltText } = render(<Header />);
    const image = getByAltText("USCIS Logo");
    expect(image.src).toContain("http://localhost/USCIS_logo_English.svg.png");
  });

  it("title", () => {
    render(<Header />);
    const headerText = screen.getByText(/Worldwide Pharmacy Chain/);
    expect(headerText).not.toBeNull();
  });

  it("sign out button", () => {
    render(<Header />);
    const button = screen.getByRole("button", { name: "Sign Out" });
    expect(button).not.toBeNull();
  });
});
