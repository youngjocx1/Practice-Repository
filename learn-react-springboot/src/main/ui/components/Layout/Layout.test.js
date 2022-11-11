import React from "react";
import { render, screen } from "@testing-library/react";
import Layout from "./Layout";

describe("Layout Component", () => {
  describe("child components", () => {
    it("renders the header component", () => {
      render(<Layout />);
      expect(screen.getByText(/Worldwide Pharmacy Chain/)).toBeInTheDocument();
    });

    it("renders the search component", () => {
      render(<Layout />);
      const searchHeader = screen.getByRole("heading", { name: "Search" });
      const lastName = screen.getByRole("textbox", { name: "Last Name" });
      expect(searchHeader).not.toBeNull();
      expect(lastName).not.toBeNull();
    });
  });
});
