package py.org.fundacionparaguaya.pspserver.security.dtos;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotNull;

public class UserDTO {

	private Long userId;

	@NotNull
	private String username;

	@NotNull
	private String pass;
	
	private boolean active;

	public UserDTO(){}

	private UserDTO(Long userId, String username, String pass, boolean active) {
		this.userId = userId;
		this.username = username;
		this.pass = pass;
		this.active = active;
	}

	public static class Builder {
		private Long userId;
		private String username;
		private String pass;
		private boolean active;

		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder pass(String pass) {
			this.pass = pass;
			return this;
		}

		public Builder active(boolean active) {
			this.active = active;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(userId, username, pass, active);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPass() {
		return pass;
	}


	public boolean isActive() {
		return active;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("userId", userId)
				.add("username", username)
				.add("pass", pass)
				.add("active", active)
				.toString();
	}
}
