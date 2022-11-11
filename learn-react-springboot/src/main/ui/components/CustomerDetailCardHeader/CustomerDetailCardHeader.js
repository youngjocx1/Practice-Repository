import { CardHeader, Tag } from "@trussworks/react-uswds";
import React from "react";
const CustomerDetailCardHeader = ({ linkedCustomers, customerIndex }) => {
  const isPrimaryRecord = () => {

    //If there is only one record, it is primary
    if (linkedCustomers.length === 1) {
      return true;
    }
    // If there are more than two records and I am past the second record, this cannot
    // be the primary record
    if (customerIndex > 1) {
      return false;
    }

    // Get the currently displayed customer date and compare against the record
    // in the [1] index (earliest birthday)
    let currentPatientDate = new Date(linkedCustomers[customerIndex]["birthDate"])
    let primaryDate = new Date(linkedCustomers[1]["birthDate"])

    // It is determined to be primary if and only if the date is on or before
    // the record in index 1 and I am currently looking at the second customer
    // This will ensure that the first record will always remain 'Primary'
    // even if the records have the same birth date
    return (currentPatientDate <= primaryDate && customerIndex === 1)
  };

  return (
    <CardHeader className="bg-primary padding-3">
      <div className="grid-row">
        <div className="grid-col flex-9">
          <h3 className="margin-0 text-white">
            Details (Record {customerIndex + 1} of {linkedCustomers.length})
            for: {linkedCustomers[customerIndex]["firstName"]}{" "}
            {linkedCustomers[customerIndex]["lastName"]}
          </h3>
        </div>
        <div className="primary-record-indicator grid-col flex-1">
          {isPrimaryRecord() && (
            <Tag className="tag margin-0">Primary Record</Tag>
          )}
        </div>
        <div className="grid-col flex-1"></div>
      </div>
    </CardHeader>
  );
};

export default CustomerDetailCardHeader;
