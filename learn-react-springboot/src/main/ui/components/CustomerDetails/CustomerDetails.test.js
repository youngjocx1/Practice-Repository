import React from "react";
import { render, waitFor, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import CustomerDetails from "./CustomerDetails";
import { mockSearchResult } from "../../utils/mockSearchData";
import * as apiSpy from "../../api/back-end";

jest.spyOn(apiSpy, "getCustomerMedicationsById");
jest.spyOn(apiSpy, "getCustomerObservationsById");
jest.spyOn(apiSpy, "getCustomerConditionsId");
jest.spyOn(apiSpy, "getCustomerEncountersById");

apiSpy.getCustomerMedicationsById.mockResolvedValueOnce({ data: [] });
apiSpy.getCustomerObservationsById.mockResolvedValueOnce({ data: [] });
apiSpy.getCustomerConditionsId.mockResolvedValueOnce({ data: [] });
apiSpy.getCustomerEncountersById.mockResolvedValueOnce({ data: [] });

describe("CustomerDetails", () => {

  it("displays customer data", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[mockSearchResult.data.patients[0]]}
        customerIndex={0}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    await waitFor(() => {
      expect(screen.getByText("8/13/1978")).toBeInTheDocument();
    });
  });

  it("shows encounters", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[mockSearchResult.data.patients[0]]}
        customerIndex={0}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    
    waitFor(() => {
      screen.getByTestId("encounters").click()
    });
    await waitFor(() => {
      const encounterText = screen.getByText("Customer Encounters");
      expect(encounterText).toBeInTheDocument();
    });
  });

  it("shows medications", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[mockSearchResult.data.patients[0]]}
        customerIndex={0}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    waitFor(() => {
      screen.findByTestId("medications").click();
    });
    await waitFor(() => {
      const medicationsText = screen.getByText("Customer Medications");
      expect(medicationsText).toBeInTheDocument();
    });
  });

  it("shows proper next when multiple ssn found", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
        ]}
        customerIndex={0}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    await waitFor(() => {
      expect(screen.findByTestId("next-button")).toBeTruthy();
    });
  });

  it("shows proper previous when multiple ssn found", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
        ]}
        customerIndex={1}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    await waitFor(() => {
      expect(screen.findByTestId("previous-button")).toBeTruthy();
    });
  });

  it("shows proper alert when multiple ssn found", async () => {
    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[0],
        ]}
        customerIndex={1}
        setScreen="customerTable"
        setCustomerIndex={jest.fn()}
      />
    );
    await waitFor(() => {
      expect(screen.findByTestId("alert")).toBeTruthy();
    });
  });

  it("simulate next record and search", async () => {
    const mockSetIndex = jest.fn();

    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[1],
        ]}
        customerIndex={0}
        setScreen="customerTable"
        setCustomerIndex={mockSetIndex}
      />
    );

    (await screen.findByTestId("next-button")).click();

    await waitFor(() => {
      expect(mockSetIndex).toHaveBeenCalled();
    });
  });

  it("simulate next record and search", async () => {
    const mockSetIndex = jest.fn();

    render(
      <CustomerDetails
        currentCustomerId={""}
        setCurrentCustomerId={jest.fn()}
        linkedCustomers={[
          mockSearchResult.data.patients[0],
          mockSearchResult.data.patients[1],
        ]}
        customerIndex={1}
        setScreen="customerTable"
        setCustomerIndex={mockSetIndex}
      />
    );

    (await screen.findByTestId("previous-button")).click();

    await waitFor(() => {
      expect(mockSetIndex).toHaveBeenCalled();
    });
  });
});
