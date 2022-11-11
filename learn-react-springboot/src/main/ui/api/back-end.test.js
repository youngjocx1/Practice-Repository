import axios from "axios";
import config from "../config";
import {
  getCustomerById,
  getCustomerMedicationsById,
  getCustomerObservationsById,
  getCustomerConditionsId,
  getCustomerEncountersById,
  getCustomerRelatedBySSN,
  getCustomersPaginated,
} from "./back-end";
jest.mock("axios");

describe("API Calls", () => {
  it("returns paged search results for given criteria", async () => {
    const expected = { data: "" };
    axios.post.mockResolvedValueOnce(expected);
    const actual = await getCustomersPaginated("patient-zero", 5, 50);

    expect(axios.post).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/paginated?pageNumber=5&maxPerPage=50`,
      "patient-zero"
    );
    expect(actual).toBe(expected);
  });

  it("returns customer information by ID", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerById(123);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123`
    );
    expect(actual).toBe(expected);
  });

  it("returns customer medications by ID", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerMedicationsById(123);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123/medications`
    );
    expect(actual).toBe(expected);
  });

  it("returns customer observations by ID", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerObservationsById(123);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123/observations`
    );
    expect(actual).toBe(expected);
  });

  it("returns customer conditions by ID", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerConditionsId(123);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123/conditions`
    );
    expect(actual).toBe(expected);
  });

  it("returns customer encounters by ID", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerEncountersById(123);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123/encounters`
    );
    expect(actual).toBe(expected);
  });

  it("returns related customer records by SSN", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    const actual = await getCustomerRelatedBySSN(123, 123456789);

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123/related/123456789`
    );
    expect(actual).toBe(expected);
  });

  it("paginated customers", async () => {
    const expected = { data: "" };
    axios.get.mockResolvedValueOnce(expected);
    await getCustomersPaginated();

    expect(axios.get).toHaveBeenCalledWith(
      `${config.services.backEnd.uri}/patients/123`
    );
  });
});
