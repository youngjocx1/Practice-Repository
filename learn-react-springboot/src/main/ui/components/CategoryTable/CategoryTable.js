import React from "react";
import DataTable from "react-data-table-component";
import Spinner from "../Spinner/Spinner";

const CategoryTable = ({ data, loading, title, mappings }) => {
  return (
    <DataTable
      title={title}
      columns={mappings}
      data={data}
      progressPending={loading}
      progressComponent={<Spinner />}
      pagination
      responsive
      fixedHeader
      fixedHeaderScrollHeight="400px"
    />
  );
};

export default CategoryTable;
