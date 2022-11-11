import React from "react";
import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Search from "./Search";

describe("Search Component", () => {
  it("Should display valid last name when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-last-name");
    await userEvent.type(inputEl, "Mock_Last");

    expect(screen.getByTestId("search-input-last-name")).toHaveValue(
      "Mock_Last"
    );
  });

  it("Should display valid first name when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-first-name");
    await userEvent.type(inputEl, "Mock_First");

    expect(screen.getByTestId("search-input-first-name")).toHaveValue(
      "Mock_First"
    );
  });

  it("Should display valid dob when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-date-of-birth");
    await userEvent.type(inputEl, "03/04/1999");

    expect(screen.getByTestId("search-input-date-of-birth")).toHaveValue(
      "03/04/1999"
    );
  });

  it("Should display valid ssn when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-social");
    await userEvent.type(inputEl, "111-11-1111");

    expect(screen.getByTestId("search-input-social")).toHaveValue(
      "111-11-1111"
    );
  });

  it("Should display valid drivers license when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-license");
    await userEvent.type(inputEl, "D-111-11-1111");

    expect(screen.getByTestId("search-input-license")).toHaveValue(
      "D-111-11-1111"
    );
  });

  it("Should display valid address when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-address");
    await userEvent.type(inputEl, "123 Lane");

    expect(screen.getByTestId("search-input-address")).toHaveValue("123 Lane");
  });

  it("Should display valid city when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-address-city");
    await userEvent.type(inputEl, "Grove");

    expect(screen.getByTestId("search-input-address-city")).toHaveValue(
      "Grove"
    );
  });

  it("Should display valid city when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-address-city");
    await userEvent.type(inputEl, "Grove");

    expect(screen.getByTestId("search-input-address-city")).toHaveValue(
      "Grove"
    );
  });

  it("Should display valid state when entered", async () => {
    render(<Search />);

    const inputEl = screen.getByTestId("search-input-state");
    await userEvent.selectOptions(inputEl, "Alabama");

    expect(screen.getByTestId("search-input-state")).toHaveValue("Alabama");
  });

  it("Should clear results when clicked", async () => {
    render(<Search setScreen={jest.fn()} setSearchCriteria={jest.fn()} />);

    const inputEl = screen.getByTestId("search-input-address");
    await userEvent.type(inputEl, "123 Lane");

    const inputEl2 = screen.getByTestId("search-input-address-city");
    await userEvent.type(inputEl2, "Grove");

    (await screen.findByTestId("clear")).click();
    await waitFor(() => {
      expect(screen.getByTestId("search-input-address")).toHaveValue("");
      expect(screen.getByTestId("search-input-address-city")).toHaveValue("");
    });
  });

  it("Should submit results when clicked", async () => {
    const mockSetSearch = jest.fn();

    render(<Search setScreen={jest.fn()} setSearchCriteria={mockSetSearch} />);

    (await screen.findByTestId("submit")).click();
    await waitFor(() => {
      expect(mockSetSearch).toHaveBeenCalled();
    });
  });
});
