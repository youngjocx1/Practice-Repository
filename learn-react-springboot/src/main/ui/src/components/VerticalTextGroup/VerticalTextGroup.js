import React from "react";
import "./VerticalTextGroup.scss";

const VerticalTextGroup = ({ label, value }) => {
  return (
    <div className="vertical-text-group">
      <p className="text-group-label margin-0"> {label} </p>
      <h4 data-testid={label} className="margin-0">
        {value}
      </h4>
    </div>
  );
};

export default VerticalTextGroup;
