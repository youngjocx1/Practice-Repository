import React, { useState } from "react";
import { Card, CardBody, Button, CardHeader } from "@trussworks/react-uswds";
import CustomerTable from "../CustomerTable/CustomerTable";
import CustomerDetails from "../CustomerDetails/CustomerDetails";
import Search from "../Search/Search";
import Header from "../Header/Header";

import "./Layout.scss";
import CustomerDetailCardHeader from "../CustomerDetailCardHeader/CustomerDetailCardHeader";
import Spinner from "../Spinner/Spinner";

const Layout = () => {
  const [screen, setScreen] = useState("customerTable");
  const [searchCriteria, setSearchCriteria] = useState({
    address: null,
    birthDate: null,
    drivers: null,
    firstName: null,
    lastName: null,
    middleInitial: null,
    ssn: null,
    addressLine1: null,
    addressLine2: null,
    addressLine3: null,
    zipCode: null,
    dobDay: null,
    dobMonth: null,
    dobYear: null,
  });

  const [currentCustomerId, setCurrentCustomerId] = useState("");
  const [linkedCustomers, setLinkedCustomers] = useState([]);
  const [customerIndex, setCustomerIndex] = useState(1);

  return (
    <div className="main-screen">
      <Header />
      <div className="content grid-row">
        <div className="grid-col flex-1 bg-gray-10" />
        <Search setSearchCriteria={setSearchCriteria} setScreen={setScreen} />
        <div className="grid-col flex-4 padding-left-5 padding-right-5">
          <Card className="content-card" gridLayout={{ tablet: { col: 12 } }}>
            {screen === "customerDetails" && (
              <CustomerDetailCardHeader
                linkedCustomers={linkedCustomers}
                customerIndex={customerIndex}
              />
            )}
            <CardBody className="padding-top-3">
              <div className="usa-card padding-1">
                {screen === "customerTable" && (
                  <CustomerTable
                    setCurrentCustomerId={setCurrentCustomerId}
                    searchCriteria={searchCriteria}
                    setLinkedCustomers={setLinkedCustomers}
                    setCustomerIndex={setCustomerIndex}
                    setScreen={setScreen}
                  />
                )}
                {screen === "customerDetails" && (
                  <CustomerDetails
                    currentCustomerId={currentCustomerId}
                    setCurrentCustomerId={setCurrentCustomerId}
                    linkedCustomers={linkedCustomers}
                    customerIndex={customerIndex}
                    setScreen={setScreen}
                    setCustomerIndex={setCustomerIndex}
                  />
                )}
                {screen === "loading" && <Spinner />}
              </div>
            </CardBody>
          </Card>
        </div>
        <div className="grid-col flex-1 bg-gray-10" />
      </div>
    </div>
  );
};

export default Layout;
