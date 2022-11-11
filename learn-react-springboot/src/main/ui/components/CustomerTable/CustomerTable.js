import React, { useCallback } from "react";
import { useState, useEffect } from "react";
import {
  getCustomersPaginated,
  getCustomerRelatedBySSN,
  getCustomerById,
} from "../../api/back-end";

import DataTable from "react-data-table-component";
import { Button } from "@trussworks/react-uswds";
import Spinner from "../Spinner/Spinner";

const CustomerTable = ({
  setLinkedCustomers,
  setCurrentCustomerId,
  setCustomerIndex,
  searchCriteria,
  setScreen,
}) => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [totalRows, setTotalRows] = useState(0);
  const [perPage, setPerPage] = useState(10);

  const handleClick = async (event, id) => {
    event.preventDefault();
    setScreen("loading");

    const response = await getCustomerById(id);
    const related = await getCustomerRelatedBySSN(id, response.data["ssn"]);

    const records = [response.data, ...related.data];

    setCurrentCustomerId(id);
    setLinkedCustomers(records);
    setCustomerIndex(0);
    setScreen("customerDetails");
  };

  const columns = [
    {
      name: "Last Name",
      width: "200px",
      sortable: true,
      selector: (row) => row.lastName,
    },
    {
      name: "First Name",
      width: "200px",
      sortable: true,
      selector: (row) => row.firstName,
    },
    {
      name: "DOB",
      sortable: true,
      sortFunction: (a, b) => {
        return new Date(a.birthDate) - new Date(b.birthDate);
      },
      selector: (row) => row.birthDate,
    },
    {
      name: "SSN",
      width: "200px",
      selector: (row) => row.ssn,
    },
    {
      name: "Address",
      width: "200px",
      selector: (row) => row.address,
    },
    {
      name: "DL",
      width: "100px",
      selector: (row) => row.drivers,
    },
    {
      name: "Patient ID",
      width: "200px",
      selector: (row) => row.patientId,
    },
    {
      name: "Action",
      width: "200px",
      cell: (row) => (
        <Button onClick={(e) => handleClick(e, row.patientId)}>Details</Button>
      ),
    },
  ];

  const fetchUsers = async (page) => {
    setLoading(true);

    const response = await getCustomersPaginated(searchCriteria, page, perPage);

    setData(response.data.patients);
    setTotalRows(response.data.totalCount);
    setLoading(false);
  };

  const handlePageChange = (page) => {
    fetchUsers(page);
  };

  const handlePerRowsChange = async (newPerPage, page) => {
    setLoading(true);

    const response = await getCustomersPaginated(
      searchCriteria,
      page,
      newPerPage
    );
    setData(response.data.patients);
    setPerPage(newPerPage);
    setLoading(false);
  };

  useEffect(() => {
    fetchUsers(1); // fetch page 1 of users
  }, [searchCriteria]);

  return (
    <DataTable
      title="Here are the customer search results based on the attributes you selected: "
      columns={columns}
      data={data}
      progressPending={loading}
      progressComponent={<Spinner />}
      pagination
      paginationServer
      paginationTotalRows={totalRows}
      onChangeRowsPerPage={handlePerRowsChange}
      onChangePage={handlePageChange}
      responsive
      fixedHeader
      fixedHeaderScrollHeight="700px"
    />
  );
};

export default CustomerTable;
