import React from "react";
import { render, screen, waitFor } from "@testing-library/react";
import CustomerTable from "./CustomerTable";
import {
  searchCriteria,
  mockSearchResult,
  mockSearchResultSingle,
  mockMultipageSearchResult,
} from "../../utils/mockSearchData";
import * as apiSpy from "../../api/back-end";

// Spies
const customersPaginatedSpy = jest.spyOn(apiSpy, "getCustomersPaginated");
const getCustomerByIdSpy = jest.spyOn(apiSpy, "getCustomerById");
const getCustomerRelatedBySSNSpy = jest.spyOn(
  apiSpy,
  "getCustomerRelatedBySSN"
);

describe("CustomerTable", () => {
  it("has basic PII in a table view", async () => {
    apiSpy.getCustomersPaginated.mockResolvedValueOnce(mockSearchResult);
    const thisCriteria = {
      ...searchCriteria,
      ssn: "420-99-1776",
    };
    render(
      <CustomerTable
        setCurrentCustomerId={""}
        searchCriteria={thisCriteria}
        setLinkedCustomers={[]}
        setCustomerIndex={1}
        setScreen={"customerTable"}
      />
    );

    await waitFor(() => {
      const firstName = screen.getByText(/Snoop/);
      const lastName = screen.getByText(/Dogg/);
      const dob = screen.getByText(/8\/13\/1978/);
      expect(firstName).toBeInTheDocument();
      expect(lastName).toBeInTheDocument();
      expect(dob).toBeInTheDocument();
    });
  });
});
