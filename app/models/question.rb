class Question < ActiveRecord::Base
  has_many :concepts_questions, dependent: :destroy
  has_many :concepts, through: :concepts_questions

  store :variables

  before_create :ensure_variables_presense

  def ensure_variables_presense
    self.variables ||= {}
  end

  def extrapolate_variables
    eval("\"#{body}\"", generate_bindings, __FILE__, __LINE__)
  end

  def generate_bindings
    variables.each do |v, s|
      instance_variable_set(v, eval(s))
    end
    binding
  end
end
