import React from "react";
import { render, screen } from "@testing-library/react";
import VerticalTextGroup from "./VerticalTextGroup";

describe("VerticalTextGroup", () => {
  it("DOB", () => {
    const { container } = render(<VerticalTextGroup
      label={"DOB"}
      value={['01/20/1978']}
    />)
    const label = container.getElementsByClassName('vertical-text-group');
    expect(label.length).toBe(1);
  });
});
