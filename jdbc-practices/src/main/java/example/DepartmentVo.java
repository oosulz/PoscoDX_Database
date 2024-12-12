package example;

public class DepartmentVo {
	private Long id;
	private String name;
	public DepartmentVo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Override
    public String toString() {
        return "DepartmentVo " +
                "[departmentId=" + id +
                ", departmentName='" + name + '\'' +
                // ... 다른 필드들 추가 ...
                ']';
    }
}
	


