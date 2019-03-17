class Review < ActiveRecord::Base
  belongs_to :concept
  belongs_to :question
  before_save :cache_easiness_factor

  store :concept_ratings

  def cache_easiness_factor
    self.easiness_factor = calculate_easiness_factor
  end

  def calculate_easiness_factor
    return 2.5 if previous_review.blank?
    return previous_review.easiness_factor if rating < 3

    previous_review.easiness_factor - 0.8 + 0.28 * quality - 0.02 * quality**2
  end

  def previous_review
    concept.reviews.take_while { |r| r.id != id }.last
  end
end
