import React from "react";
import { render, waitFor, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import CustomerDetailCardHeader from "./CustomerDetailCardHeader";
import { mockSearchResult } from "../../utils/mockSearchData";
import * as apiSpy from "../../api/back-end";
describe("CustomerDetailCardHeader", () => {
  it("display three records primary is second show record 3", async () => {
    let firstPatient = mockSearchResult.data.patients[0];
    let secondPatient = mockSearchResult.data.patients[0];
    secondPatient.birthDate = "8/13/1979";
    render(
      <CustomerDetailCardHeader
        linkedCustomers={[secondPatient, firstPatient, secondPatient]}
        customerIndex={2}
      />
    );
    await waitFor(() => {
      expect(
        screen.getByText("Details (Record 3 of 3) for: Snoop Dogg")
      ).toBeInTheDocument();
    });
  });

  it("display one primary primary", async () => {
    let firstPatient = mockSearchResult.data.patients[0];
    render(
      <CustomerDetailCardHeader
        linkedCustomers={[firstPatient]}
        customerIndex={0}
      />
    );
    await waitFor(() => {
      expect(
        screen.getByText("Details (Record 1 of 1) for: Snoop Dogg")
      ).toBeInTheDocument();
    });
  });

  it("selects prime if earliest birthday", async () => {
    let firstPatient = mockSearchResult.data.patients[0];
    let secondPatient = mockSearchResult.data.patients[0];
    firstPatient.birthDate = "8/13/1969";
    secondPatient.birthDate = "8/13/1979";
    render(
      <CustomerDetailCardHeader
        linkedCustomers={[firstPatient, secondPatient]}
        customerIndex={0}
      />
    );
    await waitFor(() => {
      expect(
        screen.getByText("Details (Record 1 of 2) for: Snoop Dogg")
      ).toBeInTheDocument();
    });
  });
});
