package The.Geeks.ResmProject.message;

import java.util.Objects;

public class ResponseMessage {
  Boolean successful;
  String error;

  public ResponseMessage() {
  }

  public ResponseMessage(Boolean successful, String error) {
    this.successful = successful;
    this.error = error;
  }

  public Boolean isSuccessful() {
    return this.successful;
  }

  public Boolean getSuccessful() {
    return this.successful;
  }

  public void setSuccessful(Boolean successful) {
    this.successful = successful;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public ResponseMessage successful(Boolean successful) {
    setSuccessful(successful);
    return this;
  }

  public ResponseMessage error(String error) {
    setError(error);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof ResponseMessage)) {
      return false;
    }
    ResponseMessage responseMessage = (ResponseMessage) o;
    return Objects.equals(successful, responseMessage.successful) && Objects.equals(error, responseMessage.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(successful, error);
  }

  @Override
  public String toString() {
    return "{" +
        " successful='" + isSuccessful() + "'" +
        ", error='" + getError() + "'" +
        "}";
  }
}